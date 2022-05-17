package com.example.foode.Product

import com.example.foode.Product.Exception.ProductNotFoundException
import spock.lang.Specification

import java.time.LocalDate

class ProductServiceSpock extends Specification {

    private ProductRepository productRepository
    private ProductService productService

    private Product product

    void setup() {
        productRepository = Mock(ProductRepository)
        productService = new ProductService(productRepository)

        product = new Product(
                1,
                "apple",
                LocalDate.of(2022, 03, 02))
    }

    def "createProduct() when called with product SHOULD return same product"() {
        given: "mocked productRepository"
        productRepository.saveAndFlush(_ as Product) >> product

        when: "createProduct() returns newly created product"
        def result = productService.createProduct(product)

        then: "returned product is same as we given in parameter"
        result == product
    }

    def "createProduct() when called with product SHOULD call saveAndFlush() method from productRepository once"() {
        given: "mocked productRepository"
        productRepository.saveAndFlush(_ as Product) >> product

        when: "we run createProduct()"
        productService.createProduct(product)

        then: "saveAndFlush() is called once"
        1 * productRepository.saveAndFlush(_ as Product) >> product
    }

    def "getProduct() when called with id, which is found in productRepository SHOULD return product with given id"() {
        given: "mocked productRepository"
        productRepository.findById(_ as Long) >> Optional.of(product)

        and: "searched product id"
        def productId = 1

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

        then:
        def productNotFoundException = thrown(ProductNotFoundException)
        productNotFoundException.message == "There is no such product with id: " + productId
    }

    def "getProduct() when called with id SHOULD call findById() method from ProductRepository once"() {
        given: "mocked productRepository"
        productRepository.findById(_ as Long) >> Optional.of(product)

        and: "searched product id"
        def productId = 1

        when: "getProduct() returns product with given id"
        productService.getProduct(productId)

        then: "returned product has given id"
        1 * productRepository.findById(_ as Long) >> Optional.of(product)
    }

}
