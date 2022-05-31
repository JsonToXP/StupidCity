package com.stupid.common.api.core.pojo;

import javax.validation.groups.Default;

public class ParamValidationGroup {

    public interface Insert extends Default{};

    public interface Update extends Default{};

    public interface Delete extends Default{};

    public interface QueryOne extends Default{};

    public interface QueryPage extends Default{};

}

