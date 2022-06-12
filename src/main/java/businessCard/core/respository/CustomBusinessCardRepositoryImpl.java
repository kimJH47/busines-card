package businessCard.core.respository;

import businessCard.core.domain.BusinessCard;
import businessCard.core.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static businessCard.core.domain.QBusinessCard.businessCard;

@RequiredArgsConstructor
public class CustomBusinessCardRepositoryImpl implements CustomBusinessCardRepository {


    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BusinessCard> findBusinessCards(BusinessCardSearch businessCardSearch, User user) {

        return jpaQueryFactory.selectFrom(businessCard)
                              .where(businessCard.user.ne(user),nameEq(businessCardSearch.getName()), emailEq(businessCardSearch.getEmail()), companyEq(businessCardSearch.getCompany()))
                              .limit(100)
                              .fetch();
    }

    private BooleanExpression nameEq(String name) {
        return name != null ? businessCard.name.eq(name) : null;
    }

    private BooleanExpression companyEq(String company) {
        return company != null ? businessCard.company.eq(company) : null;
    }

    private BooleanExpression emailEq(String email) {
        return email != null ? businessCard.email.eq(email) : null;
    }

    private BooleanExpression telEq(String tel) {
        return tel != null ? businessCard.tel.eq(tel) : null;
    }
}
