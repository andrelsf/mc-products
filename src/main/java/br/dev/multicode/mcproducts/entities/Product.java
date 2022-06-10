package br.dev.multicode.mcproducts.entities;

import br.dev.multicode.mcproducts.api.http.requests.PatchProductRequest;
import br.dev.multicode.mcproducts.api.http.requests.ProductRequest;
import br.dev.multicode.mcproducts.api.http.responses.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

  @Id
  @Column(name = "product_id", nullable = false)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column(length = 200, nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Category category;

  @Column(nullable = false)
  private BigDecimal price;

  @CreationTimestamp
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  private ZonedDateTime updatedAt;

  public static Product of(ProductRequest postProductRequest) {
    return Product.builder()
      .name(postProductRequest.getName())
      .description(postProductRequest.getDescription())
      .category(Category.valueOf(postProductRequest.getCategory()))
      .price(postProductRequest.getPrice())
      .build();
  }

  public ProductResponse toResponse() {
    return ProductResponse.builder()
      .name(name)
      .description(description)
      .category(category.name())
      .price(price)
      .build();
  }

  public void fillWith(final ProductRequest putProductRequest) {
    setName(putProductRequest.getName());
    setDescription(putProductRequest.getDescription());
    setCategory(Category.valueOf(putProductRequest.getCategory()));
    setPrice(putProductRequest.getPrice());
  }

  public void fillWith(final PatchProductRequest patchProductRequest) {
    setName(defaultIfBlank(patchProductRequest.getName(), this.getName()));
    setDescription(defaultIfBlank(patchProductRequest.getDescription(), this.getDescription()));
    setCategory(Category.valueOf(defaultIfBlank(patchProductRequest.getCategory(), this.getCategory().name())));
    setPrice(defaultIfNull(patchProductRequest.getPrice(), this.getPrice()));
  }
}
