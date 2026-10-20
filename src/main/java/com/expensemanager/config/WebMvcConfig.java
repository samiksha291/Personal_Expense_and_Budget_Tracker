package com.expensemanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/dashboard").setViewName("forward:/dashboard.html");
        registry.addViewController("/budget").setViewName("forward:/budget-view.html");
        registry.addViewController("/expense-history").setViewName("forward:/expense-history.html");
        registry.addViewController("/income-history").setViewName("forward:/income-history.html");
        registry.addViewController("/transactions").setViewName("forward:/transactions.html");
        registry.addViewController("/summary").setViewName("forward:/summary.html");
        registry.addViewController("/savings-goal").setViewName("forward:/savings-goal-view.html");
        registry.addViewController("/recurring-expense").setViewName("forward:/recurring-expense-view.html");
        registry.addViewController("/edit-expense").setViewName("forward:/edit-expense.html");
    }
}
