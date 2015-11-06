/**
 * Created by zz on 2015/10/22.
 */
function attrDefault($el, data_var, default_val)
{
    if(typeof $el.data(data_var) != 'undefined')
    {
        return $el.data(data_var);
    }

    return default_val;
}