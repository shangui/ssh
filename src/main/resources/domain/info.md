存放数据库与com.feng.ssh.pojo内的实体的映射关系的.hbm.xml文件

Example: Test.hbm.xml

<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
	<class name="com.judon.wristball.pojo.实体类名"
		table="数据库内的表名">
		/*主键映射*/
		<id name="实体类成员变量名" type="java.lang.String">
			<column name="数据库字段名" />
			<generator class="uuid" />
		</id>
		/*其他字段映射*/
		<property name="PKId" type="java.lang.String">
			<column name="PKId" />
		</property>
	</class>
</hibernate-mapping>