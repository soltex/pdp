/**
 * 
 */
package cn.com.innodev.pdp.elasticsearch;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vanstone.elasticsearch.ElasticsearchCallbackWithoutResult;
import com.vanstone.elasticsearch.ElasticsearchTemplate;

/**
 * @author shipeng
 */
@ContextConfiguration(locations = { 
		"classpath*:/core-application-context.xml",
		"classpath*:META-INF/*-application-context-module.xml" }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class ElasticsearchTest {
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Test
	public void testDeleteAllMarvel() {
		elasticsearchTemplate.execute(new ElasticsearchCallbackWithoutResult() {
			@Override
			public void doInElasticSearchWithoutResult(Client client) {
				DeleteIndexResponse deleteIndexResponse = client.admin().indices().prepareDelete("*marvel*").execute().actionGet();
				System.out.println(deleteIndexResponse.isAcknowledged());
			}
		});
	}
	
}
