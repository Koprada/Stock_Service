package com.emazon.stock_service.Infrastructure.configuration;


import com.emazon.stock_service.Domain.api.IArticleService;
import com.emazon.stock_service.Domain.api.IBrandService;
import com.emazon.stock_service.Domain.api.ICategoryService;
import com.emazon.stock_service.Domain.spi.ArticlePersistencePort;
import com.emazon.stock_service.Domain.spi.BrandPersistencePort;
import com.emazon.stock_service.Domain.spi.CategoryPersistencePort;
import com.emazon.stock_service.Domain.useCase.ArticleUseCase;
import com.emazon.stock_service.Domain.useCase.BrandUseCase;
import com.emazon.stock_service.Domain.useCase.CategoryUseCase;
import com.emazon.stock_service.Infrastructure.output.jpa.adapter.ArticleJpaAdapter;
import com.emazon.stock_service.Infrastructure.output.jpa.adapter.BrandJpaAdapter;
import com.emazon.stock_service.Infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.IArticleRepository;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.IBrandRepository;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryService categoryService() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public BrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandService brandService() { return new BrandUseCase(brandPersistencePort());}

    @Bean
    public ArticlePersistencePort articlePersistencePort() {
        return new ArticleJpaAdapter(articleRepository, articleEntityMapper);
    }

    @Bean
    public IArticleService articleService() {return new ArticleUseCase(articlePersistencePort());
    }
}
