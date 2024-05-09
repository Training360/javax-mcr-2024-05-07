package hello;

import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.RelationalManagedTypes;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.H2Dialect;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.sql.IdentifierProcessing;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.Optional;

//@Configuration
@Slf4j
public class NoQuotesConfiguration extends AbstractJdbcConfiguration {

    @Override
    public JdbcMappingContext jdbcMappingContext(Optional<NamingStrategy> namingStrategy, JdbcCustomConversions customConversions, RelationalManagedTypes jdbcManagedTypes) {
        var mappingContext = super.jdbcMappingContext(namingStrategy, customConversions, jdbcManagedTypes);
        mappingContext.setForceQuote(false);
        return mappingContext;
    }

    @Override
    public Dialect jdbcDialect(NamedParameterJdbcOperations operations) {
        var jdbcDialect = super.jdbcDialect(operations);
        if (jdbcDialect instanceof H2Dialect) {
            return new H2Dialect() {
                @Override
                public IdentifierProcessing getIdentifierProcessing() {
                    return IdentifierProcessing.create(IdentifierProcessing.Quoting.ANSI, IdentifierProcessing.LetterCasing.LOWER_CASE);
                }
            };
        }
        return jdbcDialect;
    }


}
