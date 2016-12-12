import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soy.test.entity.Bean;
import org.junit.Test;

/**
 * Created by Soy on 2016/12/10.
 */
public class TestClass {

    @Test
    public void test(){
        String json = "{\n" +
                "    \"status\": 0,\n" +
                "    \"ret_msg\": \"\",\n" +
                "    \"list\": [\n" +
                "        {\n" +
                "            \"type\": \"25\",\n" +
                "            \"pay_type\": \"\",\n" +
                "            \"name\": \"提现冻结\",\n" +
                "            \"money\": 21000,\n" +
                "            \"addtime\": \"2016-12-09 22:37:43\",\n" +
                "            \"issue\": \"\",\n" +
                "            \"status\": \"\",\n" +
                "            \"id\": \"\",\n" +
                "            \"money_type\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"25\",\n" +
                "            \"pay_type\": \"\",\n" +
                "            \"name\": \"提现冻结\",\n" +
                "            \"money\": 14700,\n" +
                "            \"addtime\": \"2016-12-09 22:36:15\",\n" +
                "            \"issue\": \"\",\n" +
                "            \"status\": \"\",\n" +
                "            \"id\": \"\",\n" +
                "            \"money_type\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"25\",\n" +
                "            \"pay_type\": \"\",\n" +
                "            \"name\": \"提现冻结\",\n" +
                "            \"money\": 12500,\n" +
                "            \"addtime\": \"2016-12-09 14:36:17\",\n" +
                "            \"issue\": \"\",\n" +
                "            \"status\": \"\",\n" +
                "            \"id\": \"\",\n" +
                "            \"money_type\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"25\",\n" +
                "            \"pay_type\": \"\",\n" +
                "            \"name\": \"提现冻结\",\n" +
                "            \"money\": 22222,\n" +
                "            \"addtime\": \"2016-12-09 11:55:47\",\n" +
                "            \"issue\": \"\",\n" +
                "            \"status\": \"\",\n" +
                "            \"id\": \"\",\n" +
                "            \"money_type\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"25\",\n" +
                "            \"pay_type\": \"\",\n" +
                "            \"name\": \"提现冻结\",\n" +
                "            \"money\": 102,\n" +
                "            \"addtime\": \"2016-12-07 15:46:19\",\n" +
                "            \"issue\": \"\",\n" +
                "            \"status\": \"\",\n" +
                "            \"id\": \"\",\n" +
                "            \"money_type\": 2\n" +
                "        }\n" +
                "    ],\n" +
                "    \"trantype\": {\n" +
                "        \"10\": \"充值\",\n" +
                "        \"11\": \"提现成功\",\n" +
                "        \"12\": \"中奖\",\n" +
                "        \"13\": \"投注\",\n" +
                "        \"14\": \"撤单\",\n" +
                "        \"18\": \"返利赠送\",\n" +
                "        \"19\": \"自身返水\",\n" +
                "        \"20\": \"直接会员返水\",\n" +
                "        \"21\": \"团队返水\",\n" +
                "        \"25\": \"提现冻结\",\n" +
                "        \"32\": \"会员额度调整\",\n" +
                "        \"48\": \"手续费\"\n" +
                "    },\n" +
                "    \"transtatus\": {\n" +
                "        \"1\": \"处理中\",\n" +
                "        \"2\": \"已完成\"\n" +
                "    }\n" +
                "}";

        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        Bean bean = gson.fromJson(json,Bean.class);
        System.out.print(bean);
    }


}
