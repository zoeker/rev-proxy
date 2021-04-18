package name.zoeker.revproxy;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/")
public class ProxyController {

    private WebClient webClient;

    @RequestMapping("/**")
    @ResponseBody
    public String mirrorRest(@RequestBody String body, HttpMethod method, HttpServletRequest request) throws URISyntaxException
    {
        URI uri = new URI("http", null, server, port, request.getRequestURI(), request.getQueryString(), null);

        ResponseEntity<String> responseEntity =
                webClient.method(method).uri(uri).body(BodyInserters.fromValue(body)).exchangeToMono();

        return responseEntity.getBody();
    }}
