package com.example.dev.dto.validation.email;

import com.example.dev.repository.OwnerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class EmailDuplicationValidator implements ConstraintValidator<EmailUnique, String> {

    private final OwnerRepository ownerRepository;
    @Override
    public void initialize(EmailUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        final boolean isExistEmail = ownerRepository.existsOwnerByOwnerEmail(email);

        if (isExistEmail) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            MessageFormat.format("Email: {0} 이미 존재하는 이름 입니다.", email)
                    )
                    .addConstraintViolation();
        }

        return !isExistEmail;
    }
}
