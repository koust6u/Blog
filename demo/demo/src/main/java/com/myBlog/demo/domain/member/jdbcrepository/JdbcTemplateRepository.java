package com.myBlog.demo.domain.member.jdbcrepository;

import com.myBlog.demo.domain.member.Member;
import com.myBlog.demo.domain.member.MemberRepository;
import com.myBlog.demo.domain.member.MemberUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;


@Slf4j
public class
JdbcTemplateRepository implements MemberRepository {
    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateRepository(DataSource dataSource){
        template =new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
    }
    @Override
    public Member save(Member member) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(member);
        Number key = jdbcInsert.executeAndReturnKey(param);
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findByKey(Long key) {

        String sql = "select id, name, login_id, password from member where key=:key";
        try{
            Map<String, Object> param = Map.of("id", key);
            Member member = template.queryForObject(sql, param, getMemberRowMapper());
            return Optional.of(member);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        String sql = "select id, name, login_id, password from member where login_id=:loginId";
        try{
            Map<String, Object> param = Map.of("loginId", loginId);
            Member member = template.queryForObject(sql,param, getMemberRowMapper());
            return Optional.of(member);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }

    }

    @Override
    public void update(Long key, MemberUpdateDto updateParam) {
        String sql = "update member " +
                "set name=:name, login_id=:loginId, password=:password "+
                "where key=:key";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", updateParam.getName())
                .addValue("loginId", updateParam.getLoginId())
                .addValue("password", updateParam.getPassword());


        template.update(sql, param);

    }


    private RowMapper<Member> getMemberRowMapper(){
        return BeanPropertyRowMapper.newInstance(Member.class);
    }
}
