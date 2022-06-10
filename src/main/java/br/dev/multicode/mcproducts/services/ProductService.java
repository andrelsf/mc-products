package br.dev.multicode.mcproducts.services;

import br.dev.multicode.mcproducts.api.http.requests.PatchProductRequest;
import br.dev.multicode.mcproducts.api.http.requests.ProductRequest;
import br.dev.multicode.mcproducts.api.http.responses.ProductResponse;

import java.util.UUID;

public interface ProductService {

  ProductResponse getById(UUID productId);

  String create(ProductRequest postProductRequest);

  void update(UUID productId, ProductRequest putProductRequest);

  void updatePartialContent(UUID productId, PatchProductRequest patchProductRequest);

  void delete(UUID productId);

}
