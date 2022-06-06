package com.example.foode.product.service

import com.example.foode.offer.exception.OfferNotFoundException
import com.example.foode.product.exception.ProductNotFoundException
import com.example.foode.product.persistence.ProductEntity
import com.example.foode.product.persistence.ProductRepository
import com.example.foode.product.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import spock.lang.Specification

import java.time.LocalDate

class ProductServiceSpec extends Specification {

    private ProductRepository productRepository
    private ProductMapper productMapper
    private ProductService productService

    private ProductEntity productEntity
    private ProductEntity secondProductEntity
    private ProductEntity updatedProductEntity
    private Page<ProductEntity> allProductEntities

    private Product product
    private Product secondProduct
    private Product updatedProduct
    private Page<Product> allProducts

    private List<Product> listOfProducts

    void setup() {
        productRepository = Mock(ProductRepository)
        productMapper = Mock(ProductMapper)
        productService = new ProductService(productRepository, productMapper)

        productEntity = new ProductEntity(
                1,
                "apple",
                LocalDate.of(2022, 03, 02))

        secondProductEntity = new ProductEntity(
                1,
                "orange",
                LocalDate.of(2022, 01, 04))

        updatedProductEntity = new ProductEntity(
                1,
                "apple",
                LocalDate.of(2022, 05, 02))

        product = new Product(
                1,
                "apple",
                LocalDate.of(2022, 03, 02))

        secondProduct = new Product(
                1,
                "orange",
                LocalDate.of(2022, 01, 04))

        updatedProduct = new Product(
                1,
                "apple",
                LocalDate.of(2022, 05, 02))

        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")))
        def listOfProductEntities = new ArrayList<ProductEntity>(List.of(productEntity, secondProductEntity))
        listOfProducts = new ArrayList<Product>(List.of(product, secondProduct))

        allProductEntities = new PageImpl<>(listOfProductEntities, pageable, 100)
        allProducts = new PageImpl<>(listOfProducts, pageable, 100)
    }

    def "createProduct() when called with product SHOULD return same product"() {
        given: "mocked productRepository"
        productRepository.saveAndFlush(_ as ProductEntity) >> productEntity

        and: "mocked productMapper"
        productMapper.toProductEntity(_ as Product) >> productEntity
        productMapper.fromProductEntity(_ as ProductEntity) >> product

        when: "createProduct() returns newly created product"
        def result = productService.createProduct(product)

        then: "returned product is same as we given in parameter"
        result.getId() == product.getId()
        result.getExpirationDate() == product.getExpirationDate()
        result.getName() == product.getName()
    }

    def "createProduct() when called with product SHOULD call saveAndFlush() method from productRepository once"() {
        given: "mocked productRepository"
        productRepository.saveAndFlush(_ as ProductEntity) >> productEntity

        and: "mocked productMapper"
        productMapper.toProductEntity(_ as Product) >> productEntity
        productMapper.fromProductEntity(_ as ProductEntity) >> product

        when: "we run createProduct()"
        productService.createProduct(product)

        then: "saveAndFlush() is called once"
        1 * productRepository.saveAndFlush(_) >> productEntity
        1 * productMapper.toProductEntity(_ as Product) >> productEntity
        1 * productMapper.fromProductEntity(_ as ProductEntity) >> product
    }

    def "getProductsByName() WHEN called with name SHOULD return page of products with given name"() {
        given: "name"
        def name = "apple"

        and: "pageable object"
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")))

        and: "mocked productRepository"
        productRepository.findAllByNameContainingIgnoreCase(_ as String, _ as Pageable) >> allProductEntities

        and: "mocked productMapper"
        productMapper.fromProductEntities(_ as List<ProductEntity>) >> listOfProducts

        when: "getOffersByCity() returns Page of offers"
        Page<Product> returnedProducts = productService.getProductsByName(name, pageable)

        then: "returned offers are of size max. 5"
        returnedProducts == allProducts
    }

    def "getProductsByName() WHEN called with name SHOULD call findAllByName() method from productsRepository once"() {
        given: "name"
        def name = "apple"

        and: "pageable object"
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")))

        and: "mocked productRepository"
        productRepository.findAllByNameContainingIgnoreCase(_ as String, _ as Pageable) >> allProducts

        and: "mocked productMapper"
        productMapper.fromProductEntities(_ as List<ProductEntity>) >> listOfProducts

        when: "we run getProductsByName"
        productService.getProductsByName(name, pageable)

        then: "findAllByName() is called once"
        1 * productRepository.findAllByNameContainingIgnoreCase(_, _) >> allProducts
        1 * productMapper.fromProductEntities(_ as List<ProductEntity>) >> listOfProducts
    }

    def "getProduct() when called with id, which is found in productRepository SHOULD return product with given id"() {
        given: "mocked productRepository"
        productRepository.findById(_ as Long) >> Optional.of(productEntity)

        and: "searched product id"
        def productId = 1

        and: "mocked productMapper"
        productMapper.fromProductEntity(_ as ProductEntity) >> product

        when: "getProduct() returns product with given id"
        def returnedProduct = productService.getProduct(productId)

        then: "returned product has given id"
        returnedProduct.getId() == productId
    }

    def "getProduct() when called with id, which is not found in productRepository SHOULD throw ProductNotFoundException()"() {
        given: "mocked productRepository"
        productRepository.findById(_ as Long) >> Optional.empty()

        and: "searched product id"
        def productId = 1

        when: "we run getProduct() with given id"
        productService.getProduct(productId)

        then: "there is thrown ProductNotFoundException"
        def productNotFoundException = thrown(ProductNotFoundException)
        productNotFoundException.message == "There is no such product with id: " + productId
    }

    def "getProduct() when called with id SHOULD call findById() method from ProductRepository once"() {
        given: "mocked productRepository"
        productRepository.findById(_ as Long) >> Optional.of(productEntity)

        and: "searched product id"
        def productId = 1

        and: "mocked productMapper"
        productMapper.fromProductEntity(_ as ProductEntity) >> product

        when: "we run getProduct() with given id"
        productService.getProduct(productId)

        then: "findById() is called once"
        1 * productRepository.findById(_) >> Optional.of(productEntity)
        1 * productMapper.fromProductEntity(_ as ProductEntity) >> product
    }

    def "deleteProduct() when called with id SHOULD call deleteById() from ProductRepository once"() {
        given: "product id to delete"
        def productId = 1

        when: "we run deleteProduct() with given id"
        productService.deleteProduct(productId)

        then: "deleteById() is called once"
        1 * productRepository.deleteById(productId)
    }

    def "updateProduct() when called with product and existing id SHOULD return original updated product of given id"() {
        given: "product id"
        def productId = 1

        and: "mocked productRepository"
        productRepository.findById(_ as Long) >> Optional.of(productEntity)
        productRepository.saveAndFlush(_ as ProductEntity) >> productEntity

        and: "mocked productMapper"
        productMapper.toProductEntity(_ as Product) >> productEntity
        productMapper.fromProductEntity(_ as ProductEntity) >> product

        when: "updateProduct() returns product with given productId"
        def returnedProduct = productService.updateProduct(updatedProduct, productId)

        then: "returned product is a same record which we saved first"
        returnedProduct.getName() == updatedProduct.getName()
        returnedProduct.getExpirationDate() == updatedProduct.getExpirationDate()
        returnedProduct.getId() == updatedProduct.getId()
    }

    def "updateProduct() when called with product and non-existing id SHOULD throw ProductNotFoundException"() {
        given: "product id"
        def productId = 1

        and: "mocked productRepository"
        productRepository.findById(_ as Long) >> Optional.empty()
        productRepository.saveAndFlush(_ as ProductEntity) >> productEntity

        when: "updateProduct() returns product with given productId"
        productService.updateProduct(updatedProduct, productId)

        then: "there should be thrown ProductNotFoundException"
        def productNotFoundException = thrown(ProductNotFoundException)
        productNotFoundException.message == "There is no such product with id: " + productId
    }

    def "updateProduct() WHEN called with product and id SHOULD call saveAndFlush() method of productRepository once"() {
        given: "product id"
        def productId = 1

        and: "mocked productRepository"
        productRepository.findById(_ as Long) >> Optional.of(productEntity)
        productRepository.saveAndFlush(_ as ProductEntity) >> productEntity

        and: "mocked productMapper"
        productMapper.toProductEntity(_ as Product) >> productEntity
        productMapper.fromProductEntity(_ as ProductEntity) >> product

        when: "we run updateProduct"
        productService.updateProduct(updatedProduct, productId)

        then: "saveAndFlush() is called once"
        1 * productRepository.findById(_ as Long) >> Optional.of(productEntity)
        1 * productRepository.saveAndFlush(_) >> productEntity
        1 * productMapper.toProductEntity(_ as Product) >> productEntity
        2 * productMapper.fromProductEntity(_ as ProductEntity) >> product
    }

}
