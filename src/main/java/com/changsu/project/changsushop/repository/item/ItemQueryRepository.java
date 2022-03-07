package com.changsu.project.changsushop.repository.item;

import com.changsu.project.changsushop.controller.dto.ItemSearchCondition;
import com.changsu.project.changsushop.domain.item.Item;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.changsu.project.changsushop.domain.item.QItem.*;

@Repository
public class ItemQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ItemQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Item> search(ItemSearchCondition condition){
        return queryFactory
                .select(item)
                .from(item)
                .where(
                        dtypeEq(condition.getDtype()),
                        searchTypeEq(condition.getSearchType(), condition.getSearchText()))
                .fetch();
    }

    public Page<Item> searchPageItems(ItemSearchCondition condition, Pageable pageable) {
        List<Item> items = queryFactory
                .select(item)
                .from(item)
                .where(
                        dtypeEq(condition.getDtype()),
                        searchTypeEq(condition.getSearchType(), condition.getSearchText())
                )
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(item.count())
                .from(item)
                .where(
                        dtypeEq(condition.getDtype()),
                        searchTypeEq(condition.getSearchType(), condition.getSearchText())
                );

        return PageableExecutionUtils.getPage(items, pageable, countQuery::fetchOne);

    }

    private BooleanExpression searchTypeEq(String searchType, String searchText) {
        if(StringUtils.hasText(searchType) && StringUtils.hasText(searchText)){
            if (searchType.equals("name")) {
                return item.name.contains(searchText);
            }
        }
        return null;
    }

    private BooleanExpression dtypeEq(String dtype) {
        if (StringUtils.hasText(dtype)) {
            if(dtype.equals("ALL")){
                return null;
            }else{
                return item.dtype.eq(dtype);
            }
        }
        return null;

    }

//    private BooleanExpression categoryEq(Long categoryItemId) {
//        if(categoryItemId != 0 && categoryItemId != null){
//            return item.categoryItems.
//        }
//
//        return null;
//    }

}
