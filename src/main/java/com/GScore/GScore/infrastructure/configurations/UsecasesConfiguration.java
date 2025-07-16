package com.GScore.GScore.infrastructure.configurations;

import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import com.GScore.GScore.application.port.repositories.UserRepository;
import com.GScore.GScore.application.port.services.JwtService;
import com.GScore.GScore.application.port.services.PasswordEncoder;
import com.GScore.GScore.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsecasesConfiguration {
    @Bean
    public GetExamResultByIdUseCase getExamResultByIdUseCase(ExamResultRepository repository) {
        return new GetExamResultByIdUseCase(repository);
    }

    @Bean
    public GetTop10GroupAUseCase getTop10GroupAUseCase(ExamResultRepository repository) {
        return new GetTop10GroupAUseCase(repository);
    }

    @Bean
    public GenerateScoreLevelReportUseCase generateScoreLevelReportUseCase(ExamResultRepository examResultRepository) {
        return new GenerateScoreLevelReportUseCase(examResultRepository);
    }

    @Bean
    public GenerateSubjectScoreLevelChartUseCase generateSubjectScoreLevelChartUseCase(ExamResultRepository examResultRepository) {
        return new GenerateSubjectScoreLevelChartUseCase(examResultRepository);
    }

    @Bean
    public GetOverviewReportUseCase getOverviewReportUseCase(ExamResultRepository examResultRepository) {
        return new GetOverviewReportUseCase(examResultRepository);
    }

    @Bean
    public LoginUsecase loginUsecase(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        return new LoginUsecase(userRepository, passwordEncoder, jwtService);
    }
}
