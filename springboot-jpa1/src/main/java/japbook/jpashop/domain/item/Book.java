package japbook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;
}
