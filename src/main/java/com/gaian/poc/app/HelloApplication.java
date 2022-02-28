package com.gaian.poc.app;

import com.gaian.poc.model.Tutorial;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication(scanBasePackages = "com.gaian.poc.*", exclude = {
    DataSourceAutoConfiguration.class})
public class HelloApplication implements CommandLineRunner {

    //implements CommandLineRunner
    @Autowired
    @Qualifier("postgreSqlJdbcTemplate")
    private JdbcTemplate postgreSqlJdbcTemplate;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(HelloApplication.class, args);
//        int id1 = 0000;//9999
//		int id2=000; //999

//        FileWriter fileWriter = new FileWriter("/home/gaian/Downloads/medium_grp_data.txt");
//        for (int id2 = 0; id2 <= 1; id2++) {
//            for (int id1 = 0; id1 < 1; id1++) {
//                String id = "61fbd2e4107c33" + String.format("%04d", id1) + "eb" + String
//                    .format("%03d", id2) + "a";
//                String message = "{\"group\":{\"id\":\"" + id
//                    + "\",\"initPopulated\":true,\"tenantID\":\"61fbd2d2c8222d0001d42b17\",\"active\":true,\"draft\":false,\"readAccess\":\"PRIVATE\",\"visibilty\":\"PUBLIC\",\"updatedTimeMs\":1643893476995,\"createdTimeMs\":1643893476995,\"tenantsWithReadAccess\":[],\"name\":\"NWS Alert for weather\",\"desc\":\"NWS Alert for weather\",\"schemaId\":\"61fbd2d3c8222d0001d42b19\",\"definitionRequest\":{\"derivedAttrs\":{},\"condition\":{\"conditionType\":\"ComboCondition\",\"comboOp\":\"AND\",\"conditions\":[],\"conditionType\":\"ComboCondition\"},\"aq\":{},\"tables\":[\"61fbd2d3c8222d0001d42b19\"]},\"definition\":{\"nodeId\":\""
//                    + id
//                    + "\",\"queryIds\":[],\"groupParts\":{\"combinationType\":\"ComboGP\",\"combinationType\":\"ComboGP\",\"_op\":\"AND\",\"_abstractGPs\":[{\"combinationType\":\"GP\",\"combinationType\":\"GP\",\"groupPart\":{\"id\":\"7fe3ab1a-404d-4e5c-82ca-c8301021e196\",\"type\":\"phoenix\",\"query\":\"SELECT t0.\\\"id\\\" AS id FROM t_61fbd2d3c8222d0001d42b19_t t0\",\"definition\":{\"joins\":[],\"tables\":[\"61fbd2d3c8222d0001d42b19\"],\"schemaId\":\"61fbd2d3c8222d0001d42b19\",\"whereClause\":{\"conditionType\":\"DGComboCondition\",\"_comboOp\":\"AND\",\"_conditions\":[],\"conditionType\":\"DGComboCondition\"},\"embeddedWhereClause\":{\"conditionType\":\"DGComboCondition\",\"_comboOp\":\"AND\",\"_conditions\":[],\"conditionType\":\"DGComboCondition\"},\"aqNameMap\":{},\"attributes\":[]},\"attrs\":[]},\"unifiedQuery\":\"SELECT t0.\\\"id\\\" AS id FROM t_61fbd2d3c8222d0001d42b19_t t0\"}],\"unifiedQuery\":\"SELECT t0.\\\"id\\\" AS id FROM t_61fbd2d3c8222d0001d42b19_t t0\"},\"attributes\":[],\"split\":null,\"remSplit\":null,\"unifiedQuery\":\"SELECT t0.\\\"id\\\" AS id FROM t_61fbd2d3c8222d0001d42b19_t t0\"},\"offline\":false,\"triggers\":[],\"evaluationSchedule\":{}},\"tenantId\":\"61fbd2d2c8222d0001d42b17\",\"eventType\":\"ENTITY_ADDED\",\"schemaId\":\"61fbd2d3c8222d0001d42b19\",\"entityWrapper\":{\"_id\":\"61fbd2ffc8222d0001d42b1e\",\"tenantID\":\"61fbd2d2c8222d0001d42b17\",\"entityID\":\"TFW-NOAA-NWS-ALERTS-AK125F63E5B5F8_SpecialWeatherStatement76d80575306d689b65cfcc8186c1b4d4315a59aa19e13dcb1749e03b3e9181b0\",\"entity\":{\"identifier\":\"TFW-NOAA-NWS-ALERTS-AK125F63E5B5F8_SpecialWeatherStatement76d80575306d689b65cfcc8186c1b4d4315a59aa19e13dcb1749e03b3e9181b0\",\"sender\":\"w - nws.webmaster @noaa.gov\",\"sent\":1643893503057,\"status\":\"Actual\",\"msgType\":\"Alert\",\"scope\":\"Public\",\"note\":\"Alert for Northeastern Brooks Range(Alaska) Issued by the National Weather Service\",\"info\":{\"category\":[\"Met\"],\"event\":\"Special Weather Statement\",\"urgency\":\"Expected\",\"severity\":\"Minor\",\"certainty\":\"Observed\",\"eventCode\":{\"valueName\":\"SAME\",\"value\":\"SPS\"},\"effective\":1643893503057,\"expires\":1643893503057,\"senderName\":\"NWS Fairbanks(Northern Alaska - Fairbanks)\",\"headline\":\"Special Weather Statement issued September 07 at 11: 18 AM AKDT by NWS Fairbanks\",\"description\":\"\",\"parameter\":[{\"valueName\":\"UGC\",\"value\":\"AKZ206\"}],\"area\":{\"areaDesc\":\"Northeastern Brooks Range\",\"geocode\":[{\"valueName\":\"FIPS6\",\"value\":\"002185\"}]}}},\"creationDate\":1643893503162,\"updationDate\":0},\"attributes\":[],\"groupCount\":1,\"groupIndex\":0}";
//
//                fileWriter.append(message);
//                fileWriter.append("\n");
//                System.out.println(message);
//            }
//        }
//                fileWriter.close();
//
    }

    @Override
    public void run(String... args) throws Exception {
        String groupId = "61fbd2e4107c335458eb124a";
        String id = "61fbd2ffc8222d0001d42b1e";
        String schemaId = "61fbd2d3c8222d0001d42b19";
        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            int count = this.postgreSqlJdbcTemplate.queryForObject(
//                "select count(*) from group_metadata where groupid =? and id = ? and schemaid=?",
//                new Object[]{groupId, id, schemaId}, Integer.class);
            Tutorial tutorial = new Tutorial("Hello", "test", true);
            int insertResponse = postgreSqlJdbcTemplate
                .update("INSERT INTO tutorials (title, description, published) VALUES(?,?,?)",
                    tutorial.getTitle(), tutorial.getDescription(),
                    tutorial.isPublished());

            System.out.println("Insert id" + insertResponse);

            Tutorial reTutorial = postgreSqlJdbcTemplate
                .queryForObject("SELECT * FROM tutorials WHERE id=" + insertResponse,
                    BeanPropertyRowMapper.newInstance(Tutorial.class), id);
            System.out.println(reTutorial);

            postgreSqlJdbcTemplate.query("SELECT * from tutorials",
                BeanPropertyRowMapper.newInstance(Tutorial.class));

            System.out.println(
                "@@@@@@ Time Taken in sec: " + (System.currentTimeMillis() - start) / 1000);
//        }
    }
}
