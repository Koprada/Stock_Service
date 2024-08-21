package com.emazon.stock_service.Infrastructure.configuration;


import com.emazon.stock_service.Domain.api.ICategoriaService;
import com.emazon.stock_service.Domain.spi.CategoriaPersistencePort;
import com.emazon.stock_service.Domain.useCase.CategoriaUseCase;
import com.emazon.stock_service.Infrastructure.output.jpa.adapter.CategoriaJpaAdapter;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.CategoriaEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.ICategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoriaRepository categoriaRepository;
    private final CategoriaEntityMapper categoriaEntityMapper;

    @Bean
    public CategoriaPersistencePort categoryPersistencePort() {
        return new CategoriaJpaAdapter(categoriaRepository, categoriaEntityMapper);
    }

    @Bean
    public ICategoriaService categoriaService() {
        return new CategoriaUseCase(categoryPersistencePort());
    }
}
