/*package com.deleba.dsl.demo;
 
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
 
@Component
public class PersonAndIDCardManager {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    
    private JPAQueryFactory queryFactory;
    
    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }
    
    *//**
     * Details：多表动态查询
     *//*
    public List<Tuple> findAllPersonAndIdCard(){
        Predicate predicate = (QPerson.person.id.intValue()).eq(QIDCard.iDCard.person.id.intValue());
        JPAQuery<Tuple> jpaQuery = queryFactory.select(QIDCard.iDCard.idNo, QPerson.person.address, QPerson.person.name)
                .from(QIDCard.iDCard, QPerson.person)
                .where(predicate);
        return jpaQuery.fetch();
    }
    
    *//**
     * Details：将查询结果以DTO的方式输出
     *//*
    public List<PersonIDCardDto> findByDTO(){
        Predicate predicate = (QPerson.person.id.intValue()).eq(QIDCard.iDCard.person.id.intValue());
        JPAQuery<Tuple> jpaQuery = queryFactory.select(QIDCard.iDCard.idNo, QPerson.person.address, QPerson.person.name)
                .from(QIDCard.iDCard, QPerson.person)
                .where(predicate);
        List<Tuple> tuples = jpaQuery.fetch();
        List<PersonIDCardDto> dtos = new ArrayList<PersonIDCardDto>();
        if(null != tuples && !tuples.isEmpty()){
            for(Tuple tuple:tuples){
                String address = tuple.get(QPerson.person.address);
                String name = tuple.get(QPerson.person.name);
                String idCard = tuple.get(QIDCard.iDCard.idNo);
                PersonIDCardDto dto = new PersonIDCardDto();
                dto.setAddress(address);
                dto.setIdNo(idCard);
                dto.setName(name);
                dtos.add(dto);
            }
        }
        return dtos;
    }
    
    *//**
     * Details：多表动态查询，并分页
     *//*
    public QueryResults<Tuple> findByDtoAndPager(int offset, int pageSize){
        Predicate predicate = (QPerson.person.id.intValue()).eq(QIDCard.iDCard.person.id.intValue());
        return queryFactory.select(QIDCard.iDCard.idNo, QPerson.person.address, QPerson.person.name)
                .from(QIDCard.iDCard, QPerson.person)
                .where(predicate)
                .offset(offset)
                .limit(pageSize)
                .fetchResults();
    }
}
*/