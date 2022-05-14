package ${controllerPackage};

import ${modelReference};
import ${serviceReference};

import com.github.pagehelper.PageInfo;
import com.dwise.workeasy.model.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ${controllerName} {

    private static final Logger logger = LoggerFactory.getLogger(${controllerName}.class);

    @Autowired
    private ${serviceName} ${serviceVarName};

    @RequestMapping(value = "/${modelVarName}/{id}",method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") long id) {
        ${modelName} ${modelVarName} = ${serviceVarName}.getById(id);
        return ResponseResult.successResult(${modelVarName});
    }

    @RequestMapping(value = "/${modelVarsName}",method = RequestMethod.GET)
    public ResponseResult list${modelName}s(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize, ${modelName} ${modelVarName}) {
        PageInfo<${modelName}> pageInfo = ${serviceVarName}.list${modelName}s(pageNum, pageSize, ${modelVarName});
        return ResponseResult.successResult(pageInfo);
    }

    @RequestMapping(value="/${modelVarName}",method = RequestMethod.PUT)
    public ResponseResult update${modelName}(${modelName} ${modelVarName}) {
        if (${modelVarName} == null || ${modelVarName}.getId() == null) {
            return ResponseResult.paramErrorResult();
        }
        ${serviceVarName}.updateByIdSelective(${modelVarName});
        return ResponseResult.successResult();
    }

    @RequestMapping(value="/${modelVarName}",method = RequestMethod.POST)
    public ResponseResult add${modelName}(${modelName} ${modelVarName}) {
        if (${modelVarName} == null || ${modelVarName}.getId() != null) {
            return ResponseResult.paramErrorResult();
        }
        ${serviceVarName}.insertSelective(${modelVarName});
        return ResponseResult.successResult();
    }

    @RequestMapping(value = "/${modelVarName}/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete${modelName}(@PathVariable("id") long id) {
        ${serviceVarName}.deleteById(id);
        return ResponseResult.successResult();
    }
}
