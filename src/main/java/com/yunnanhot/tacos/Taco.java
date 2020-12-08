// tag::all[]
// tag::allButValidation[]
package com.yunnanhot.tacos;
import java.sql.Date;
import java.util.List;
// end::allButValidation[]
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
// tag::allButValidation[]
import lombok.Data;

@Data
//在编译时期自动生成必要的javabean方法，所以生成的方法在运行期是可用的。
public class Taco {


  @NotNull//字段不能为空
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<String> ingredients;

  private Long id;
  private Date createdAt;

}
//end::allButValidation[]
//tag::end[]