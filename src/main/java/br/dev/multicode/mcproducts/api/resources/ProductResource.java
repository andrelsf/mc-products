package br.dev.multicode.mcproducts.api.resources;

import br.dev.multicode.mcproducts.api.http.requests.PatchProductRequest;
import br.dev.multicode.mcproducts.api.http.requests.ProductRequest;
import br.dev.multicode.mcproducts.api.http.responses.ProductResponse;
import br.dev.multicode.mcproducts.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductResource {

  private final ProductService service;

  @PostMapping
  public ResponseEntity<Void> postANewProduct(@RequestBody @Valid ProductRequest postProductRequest)
  {
    final String productId = service.create(postProductRequest);
    final URI uriLocation = UriComponentsBuilder.fromUriString("/api/products/{productId}")
      .buildAndExpand(productId)
      .toUri();
    return ResponseEntity.created(uriLocation).build();
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID productId)
  {
    return ResponseEntity.ok(service.getById(productId));
  }

  @PutMapping("/{productId}")
  public ResponseEntity<Void> putAProduct(@PathVariable UUID productId, @RequestBody @Valid ProductRequest putProductRequest)
  {
    service.update(productId, putProductRequest);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{productId}")
  public ResponseEntity<Void> pathAProduct(@PathVariable UUID productId, @RequestBody PatchProductRequest patchProductRequest)
  {
    service.updatePartialContent(productId, patchProductRequest);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<Void> deleteAProduct(@PathVariable UUID productId)
  {
    service.delete(productId);
    return ResponseEntity.noContent().build();
  }
}
