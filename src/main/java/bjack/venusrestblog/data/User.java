package bjack.venusrestblog.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import bjack.venusrestblog.dto.UserFetchDTO;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

//@NamedNativeQuery(name = "User.fetchUserDTOs",
//        query = "SELECT id, user_name, email FROM users",
//        resultSetMapping = "Mapping.UserFetchDTO")
//@SqlResultSetMapping(name = "Mapping.UserFetchDTO",
//        classes = @ConstructorResult(targetClass = UserFetchDTO.class,
//                columns = {@ColumnResult(name = "id"),
//                        @ColumnResult(name = "user_name"),
//                        @ColumnResult(name = "email")}))

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    private String userName;

    @Email
    @NotEmpty
    @Column(nullable = false, length = 100)
    private String email;

    @ToStringExclude
    @Column(nullable = false, length = 100)
    private String password;

    @Column()
    private LocalDate createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private UserRole role;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    @ToString.Exclude
    private Collection<Post> posts;

}