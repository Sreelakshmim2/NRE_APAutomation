package utilPack;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/// <summary>
/// To parameter the testname to dataprovider
/// </summary>
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface ExcelDataSourceInfo {

	String TestName();

}