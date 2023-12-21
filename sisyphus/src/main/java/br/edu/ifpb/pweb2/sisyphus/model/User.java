@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
@Id
private String username;
private String password;
private Boolean enabled;
@OneToMany(mappedBy = "username")
@ToString.Exclude
List<Authority> authorities;
}
