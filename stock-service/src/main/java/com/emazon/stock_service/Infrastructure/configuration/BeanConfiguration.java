package com.emazon.stock_service.Infrastructure.configuration;


import com.emazon.stock_service.Domain.api.ICategoryService;
import com.emazon.stock_service.Domain.spi.CategoryPersistencePort;
import com.emazon.stock_service.Domain.useCase.CategoryUseCase;
import com.emazon.stock_service.Infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoriaRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoriaRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryService categoriaService() {
        return new CategoryUseCase(categoryPersistencePort());
    }
}
