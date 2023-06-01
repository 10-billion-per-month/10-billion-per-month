# JPA 

## build.gradle

```groovy
dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        implementation 'org.springframework.boot:spring-boot-starter-web'
        compileOnly 'org.projectlombok:lombok'
        runtimeOnly 'com.h2database:h2'
        annotationProcessor 'org.projectlombok:lombok'
        testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

dependencies 를 추가해준다.  
spring-boot-starter-data-jpa 의존성을 주입 받으면 javax.persistence.EntityManager 가 bean으로 등록되어서 사용할 수 있다.  

jpa는 interface이고 hibernate 라는 구현체를 사용한다.  
우리가 사용하는 jpa는 hibernate를 사용하는 것이다.  


# Entity의 생성자에서 id를 빼는 이유

```java
@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @Builder
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

일반적인 Member라는 엔티티이다.  
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

## insert(save)시 id를 빼는 이유
@GeneratedValue(strategy = GenerationType.IDENTITY) 가 mysql의 auto_increment와 같은 역할을 한다.  
그래서 Member 엔티티를 생성할때는 자동 증가값(id)을 뺀 후 생성해준 뒤 save를 해줘야한다.  
그렇지 않으면 JPA에서는 이미 id값이 존재하는 것으로 인식하고 update를 시도한다.


## update를 시도하는 이유
기본적으로 JPA 는 dirty checking 이라는 기능을 제공한다.  
dirty checking 이란 객체의 변경을 감지해서 자동으로 update 쿼리를 날려주는 기능이다.  
이 dirty checking 하기 위해서는 @Transactional 어노테이션이 필요하다.  
@Transactional 어노테이션을 붙이면 트랜잭션을 시작하고 트랜잭션이 끝나는 시점에 dirty checking을 한다.
그래서 entity 객체에 id 값이 있으면 select 쿼리를 DB에 날려서 값이 있는지 확인한 뒤 만약 값이 있으면 
현재 상태의 Entity 객체와 비교하여 변경된 값이 있으면 update 쿼리를 날려준다.  

그렇기 때문에 entity를 생성할때 id를 넣고 생성한뒤 save 메서드를 호출하면 jpa는 DB에 값이 있는지 확인하기 위해 
select 쿼리를 날려서 값이 있는지 확인한 뒤 있으면 insert, 없으면 update 쿼리를 날려준다. 



## 기본 생성자(@NoArgsConstructor)를 만들어 주는 이유
기본적으로 JPA는 엔티티 객체를 생성할 때 기본 생성자를 사용한다.  
조회시에도 기본 생성자를 사용해서 객체를 생성한다.  
그렇기 때문에 @NoArgsConstructor 를 통해 기본생성자를 만들어 준 뒤 이 기본생성자의 접근제한자는 protected 이상으로 생성해준다.  
그 이유는 private은 자기 자신말고는 접근할 수 없기 때문이고 protected는 상속받은 클래스에서만 접근할 수 있기 때문이다.
여기서 엔티티의 프록시는 원본 엔티티를 상속해서 만들기 때문이다.
객체를 생성한 뒤 자바의 리플렉션 API를 사용해서 객체를 생성한 뒤 조회한 값을 set 해준다.
리플렉션 API는 setter가 없어도 값을 주입할 수 있기 때문에 JPA 이 방식을 채택해서 id를 빼고 생성해도 문제가 없다.



## 예제 코드
```java
    public class Member {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String name;
    
        private int age;
    
        @Builder
        public Member(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        // 수정 메서드
        public void modifyMember(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    /**
     * 회원 정보 수정
     * @param dto
     */
    @Transactional
    public void editMember(MemberDto dto) {
        Member member = memberRepository.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + dto.getId()));
        member.modifyMember(dto.getName(), dto.getAge());
    }
```
dirty checking을 이용하여 Member 객체의 값을 변경해준뒤 save를 호출하지 않아도 update 쿼리가 날라간다.




