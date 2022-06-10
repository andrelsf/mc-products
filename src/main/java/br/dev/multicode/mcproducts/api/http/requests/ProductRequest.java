package br.dev.multicode.mcproducts.api.http.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  @NotBlank
  private String category;

  @NotNull
  @PositiveOrZero
  private BigDecimal price;

}
