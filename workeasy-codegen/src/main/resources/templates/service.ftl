package ${servicePackage};

import com.github.pagehelper.PageInfo;
import ${modelReference};

public interface ${serviceName} {

    ${modelName} getById(long id);

    void insertSelective(${modelName} ${modelVarName});

    void updateByIdSelective(${modelName} ${modelVarName});

    void deleteById(long id);

    PageInfo<${modelName}> list${modelName}s(int pageNum, int pageSize, ${modelName} ${modelVarName});
}
