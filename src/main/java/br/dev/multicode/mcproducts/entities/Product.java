package br.dev.multicode.mcproducts.entities;

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
}
