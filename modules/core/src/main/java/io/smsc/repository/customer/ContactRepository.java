package io.smsc.repository.customer;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import io.smsc.model.customer.Contact;
import io.smsc.model.customer.QContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 * This REST repository class is used for providing default {@link JpaRepository}
 * CRUD methods to operate with {@link Contact} entities and exporting them
 * to appropriate endpoints.
 *
 * @author Nazar Lipkovskyy
 * @since 0.0.1-SNAPSHOT
 */
@RepositoryRestResource(collectionResourceRel = "customer-contacts", path = "customer-contacts")
@Transactional(readOnly = true)
public interface ContactRepository extends JpaRepository<Contact, Long>,
        QueryDslPredicateExecutor<Contact>,
        QuerydslBinderCustomizer<QContact> {

    @Override
    default public void customize(QuerydslBindings bindings, QContact root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long id);

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Contact save(Contact customer);

    @Override
    @EntityGraph(attributePaths = {"type", "salutation"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Contact findOne(Long id);

    @EntityGraph(attributePaths = {"type", "salutation"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Contact findByEmailAddress(@Param("emailAddress") String emailAddress);

    @Override
    @EntityGraph(attributePaths = {"type", "salutation"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<Contact> findAll(Pageable pageable);
}