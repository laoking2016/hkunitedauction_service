package com.hkunitedauction.console.api.maindata;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.console.client.ImageClient;
import com.hkunitedauction.maindata.model.Image;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(value = "Image")
@RestController
@RequestMapping("/main-data/image")
public class ImageController {

    @Autowired
    private ImageClient client;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${feign.image.url:}")
    private String imageUrl;

    @ApiOperation("query")
    @GetMapping
    public QueryResult<Image> query(@RequestParam(value = "filter", required = false) String filter,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "pagasize", required = false) Integer pagesize,
                             @RequestParam(value = "page", required = false) Integer page){
        return client.query(filter, sort, pagesize, page);
    }

    @ApiOperation("create")
    @PostMapping
    public Long create(@RequestParam("file") MultipartFile file)  throws IOException {

        ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }

            @Override
            public long contentLength() {
                return file.getSize();
            }
        };

        MultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();
        multipartRequest.add("file", fileAsResource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity (multipartRequest, headers);

        return restTemplate.postForObject(imageUrl, requestEntity, Long.class);
    }

    @ApiOperation("delete")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        client.delete(id);
    }
}
