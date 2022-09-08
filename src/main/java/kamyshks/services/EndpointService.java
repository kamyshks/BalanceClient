package kamyshks.services;

import javax.ws.rs.client.ClientBuilder;

import kamyshks.dto.BalanceDto;
import kamyshks.dto.Params;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EndpointService {

    private final String baseUrl;

    public EndpointService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void call(final Params params) {
        if (params == null) {
            return;
        }
        final List<Integer> idList = params.getIdList();
        Runnable taskGet = () -> callGetAmount(idList);
        Runnable taskAdd = () -> callAddAmount(idList);
        for (int i=0; i < params.getWCount(); i++) {
            new Thread(taskAdd).start();
        }
        for (int i=0; i < params.getRCount(); i++) {
            new Thread(taskGet).start();
        }
    }

    private void callGetAmount(final List<Integer> idList){
        final Integer id = idList.get(getRandomIndex(idList.size()));
        final String url = baseUrl + "/api/amount/" + id;
        Response response = ClientBuilder.newBuilder().build().target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println("GET " + url + " " + response.getStatusInfo());
    }

    private void callAddAmount(final List<Integer> idList){
        final Integer id = idList.get(getRandomIndex(idList.size()));
        String url = baseUrl + "/api/amount/";
        Response response = ClientBuilder.newBuilder().build().target(url)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new BalanceDto(id, 1L), MediaType.APPLICATION_JSON));
        System.out.println("POST " + url + " " + response.getStatusInfo());
    }

    private int getRandomIndex(final int idListSize){
        return ThreadLocalRandom.current().nextInt(idListSize);
    }
}
