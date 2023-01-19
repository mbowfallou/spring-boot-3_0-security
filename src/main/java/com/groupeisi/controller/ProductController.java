package com.groupeisi.controller;

import com.groupeisi.entity.ProductEntity;
import com.groupeisi.entity.UserInfo;
import com.groupeisi.repository.IProductRepository;
import com.groupeisi.repository.UserInfoRepository;
import com.groupeisi.service.ProductService;
import com.groupeisi.service.UserInfoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private IProductRepository repository;
    @PostConstruct
    public void initUsers(){
        if(userInfoService.isListEmpty()){
            userInfoService.addUser(new UserInfo(100, "mbow@gmail.com", "fallou", "pass1", "ROLE_ADMIN, ROLE_USER"));
            userInfoService.addUser(new UserInfo(102, "thiam@gmail.com", "madicke", "pass2", "ROLE_USER"));
            userInfoService.addUser(new UserInfo(103, "ladioula@gmail.com", "alle", "pass3", "ROLE_USER"));
        }
    }

    @PostConstruct
    public void initProducts(){
        if(repository.findAll().isEmpty()) {
            repository.save(new ProductEntity(100, "Ordinateur", 10, 300000));
            repository.save(new ProductEntity(101, "Imprimante", 7, 230000));
            repository.save(new ProductEntity(102, "Cuisinière", 20, 175000));
            repository.save(new ProductEntity(103, "Ordinateur", 4, 750000));
        }
    }
    @GetMapping
    public String welcome(){
        return "Welcome this endpoint is not sure!";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<ProductEntity> getAllProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Optional<ProductEntity> getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }
}
