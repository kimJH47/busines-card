package businessCard.core.respository;

import businessCard.core.domain.BusinessCard;
import businessCard.core.domain.QBusinessCard;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static businessCard.core.domain.QBusinessCard.businessCard;

@RequiredArgsConstructor
public class BusinessCardRepositoryImpl implements CustomBusinessCardRepository {

    private final JPAQueryFactory  jpaQueryFactory;
    @Override
    public List<BusinessCard> findBusinessCards(BusinessCardSearch businessCardSearch) {
        return jpaQueryFactory.selectFrom(businessCard)
                              .where(nameEq(businessCardSearch.getName()), emailEq(businessCardSearch.getEmail()), companyEq(businessCardSearch.getCompany()))
                              .fetch();
    }

    private BooleanExpression nameEq(String name) {
        return name != null ? businessCard.name.eq(name) : null;
    }
    private BooleanExpression companyEq(String company) {
        return company != null ? businessCard.company.eq(company) : null;
    }
    private BooleanExpression emailEq(String email) {
        return email != null ? businessCard.company.eq(email) : null;
    }

}
