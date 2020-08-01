package com.hkunitedauction.maindata.api;

import com.hkunitedauction.common.response.QueryResult;
import com.hkunitedauction.maindata.mapper.ImagePOMapper;
import com.hkunitedauction.maindata.model.Image;
import com.hkunitedauction.maindata.model.ImagePO;
import com.hkunitedauction.util.File.FileUtil;
import com.hkunitedauction.util.File.StorageResult;
import com.hkunitedauction.util.File.StorageService;
import com.hkunitedauction.util.QueryBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value = "Image")
@RestController
@RequiredArgsConstructor
public class ImageFacadeImpl implements ImageFacade {

    @Autowired
    private ImagePOMapper imagePOMapper;

    @Autowired
    private Mapper dozerMapper;

    @Autowired
    private StorageService storageService;

    @ApiOperation(value = "query")
    @Override
    public QueryResult<Image> query(@RequestParam(value = "filter", required = false) String filter,
                                    @RequestParam(value = "sort", required = false) String sort,
                                    @RequestParam(value = "pagasize", required = false) Integer pagesize,
                                    @RequestParam(value = "page", required = false) Integer page) {

        Example example = new Example(ImagePO.class);
        Example.Criteria criteria = QueryBuilder.buildCriteria(example, QueryBuilder.buildParams(filter));
        Example.OrderBy orderBy = QueryBuilder.buildSortBy(example, QueryBuilder.buildParams(sort));
        RowBounds rowBounds = QueryBuilder.buildRowBounds(page, pagesize);

        int totalCount = this.imagePOMapper.selectCountByExample(example);
        Image[] list = this.imagePOMapper.selectByExampleAndRowBounds(example, rowBounds)
                .stream().map(e -> {
                    Image model = dozerMapper.map(e, Image.class);
                    return model;
                }).collect(Collectors.toList()).toArray(new Image[0]);

        QueryResult<Image> result = new QueryResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);

        return result;
    }

    @ApiOperation(value = "create")
    @Override
    public Long create(@RequestPart("file") MultipartFile file) {

        StorageResult result = null;
        ImagePO po = null;
        Date now = new Date();

        try {
            InputStream is =  file.getInputStream();
            byte[] bytes = FileUtil.toByteArray(is);

            String md5 = FileUtil.MD5(bytes);
            ImagePO query = new ImagePO();
            query.setChecksum(md5);
            po = this.imagePOMapper.selectOne(query);
            if(po == null){
                result = storageService.store(file);
                po = new ImagePO();
                po.setId(null);
                po.setName(result.getName());
                po.setIconName(result.getIconName());
                po.setPath(result.getPath());
                po.setIconPath(result.getIconPath());
                po.setVpath(result.getVpath());
                po.setIconVpath(result.getIconVpath());
                po.setUrl(result.getUrl());
                po.setIconUrl(result.getIconUrl());
                po.setChecksum(result.getCheckSum());
                po.setDeleted(false);
                po.setCreatedTime(now);
                po.setModifedTime(now);
                imagePOMapper.insert(po);

                return po.getId();
            }else{
                po.setDeleted(false);
                po.setCreatedTime(now);
                po.setModifedTime(now);
                po.setDeletedTime(null);
                imagePOMapper.updateByPrimaryKey(po);
                return po.getId();
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "delete")
    @Override
    public void delete(@PathVariable Long id) {

        ImagePO po = new ImagePO();
        po.setId(id);
        po.setDeleted(true);
        po.setDeletedTime(new Date());

        this.imagePOMapper.updateByPrimaryKeySelective(po);
    }
}
