package ${serviceImplPackage};

import ${mapperReference};
import ${serviceReference};
import ${modelReference};
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ${serviceImplName} implements ${serviceName} {

    private static final Logger logger = LoggerFactory.getLogger(${serviceImplName}.class);

    @Autowired
    private ${mapperName} ${mapperVarName};

    @Override
    public ${modelName} getById(long id) {
        if (id <= 0) {
            return null;
        }
        return ${mapperVarName}.selectByPrimaryKey(id);
    }

    @Override
    public void insertSelective(${modelName} ${modelVarName}) {
        ${mapperVarName}.insertSelective(${modelVarName});
    }

    @Override
    public void updateByIdSelective(${modelName} ${modelVarName}) {
        if (${modelVarName} == null || ${modelVarName}.getId() == null) {
            return;
        }
        ${mapperVarName}.updateByPrimaryKeySelective(${modelVarName});
    }

    @Override
    public void deleteById(long id) {
        if (id <= 0) {
            return;
        }
        ${mapperVarName}.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<${modelName}> list${modelName}s(int pageNum, int pageSize, ${modelName} model) {
        PageHelper.startPage(pageNum, pageSize);
        List<${modelName}> ${modelVarsName} = ${mapperVarName}.selectByExample(model.toExample());
        return new PageInfo<>(${modelVarsName});
    }
}
