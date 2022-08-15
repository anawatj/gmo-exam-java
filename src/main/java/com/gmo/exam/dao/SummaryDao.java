package com.gmo.exam.dao;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gmo.exam.dto.SummaryDto;



@Repository
public class SummaryDao {

	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SummaryDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	
	public SummaryDto summaryByUserName(String userName) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append( "   u.id, ");
		sql.append("    u.user_name, ");
		sql.append("    SUM(a.answer_score) AS score ");
		sql.append(" FROM users u ");
		sql.append("   JOIN user_questions uq ON u.id = uq.user_id ");
		sql.append("   JOIN user_question_answers uqa ON uq.id = uqa.user_question_id ");
		sql.append("   JOIN answers a ON uqa.answer_id = a.id ");
		sql.append(" WHERE u.user_name = ?");
		sql.append(" GROUP BY u.id,u.user_name ");
		List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql.toString(), userName);
		List<SummaryDto> results = maps.stream().map(map->{
			SummaryDto result = new SummaryDto();
			result.setUserName((String)map.get("user_name"));
			result.setScore((Long)map.get("score"));
			return result;
		}).toList();
		return results.get(0);
		
	}
}
