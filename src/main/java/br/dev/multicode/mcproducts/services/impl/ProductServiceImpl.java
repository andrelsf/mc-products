package br.dev.multicode.mcproducts.services.impl;

import br.dev.multicode.mcproducts.api.http.requests.PatchProductRequest;
import br.dev.multicode.mcproducts.api.http.requests.ProductRequest;
import br.dev.multicode.mcproducts.api.http.responses.ProductResponse;
import br.dev.multicode.mcproducts.entities.Product;
import br.dev.multicode.mcproducts.repositories.ProductRepository;
import br.dev.multicode.mcproducts.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  @Override
  public ProductResponse getById(final UUID productId) {
    return this.getProductById(productId.toString()).toResponse();
  }

  @Override
  public String create(final ProductRequest postProductRequest) {
    return repository.save(Product.of(postProductRequest)).getId();
  }

  @Override
  public void update(final UUID productId, final ProductRequest putProductRequest) {
    Product product = this.getProductById(productId.toString());
    product.fillWith(putProductRequest);
    repository.save(product);
  }

  @Override
  public void updatePartialContent(final UUID productId, final PatchProductRequest patchProductRequest) {
    Product product = this.getProductById(productId.toString());
    product.fillWith(patchProductRequest);
    repository.save(product);
  }

  @Override
  public void delete(final UUID productId) {
    repository.delete(this.getProductById(productId.toString()));
  }

  private Product getProductById(final String productId) {
    return repository.findById(productId)
      .orElseThrow(() ->
        new IllegalArgumentException("Product not found by ID=".concat(productId)));
  }
}
