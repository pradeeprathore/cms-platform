package com.ascent.cms.core.util;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ascent.cms.core.domain.Model;

@Component("assembler")
public class DomainUtils
{

	public static void copyNotNullProperties(Object destination, Object source)
	{
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(source);
		if (descriptors != null)
		{
			String propertyName = null;
			for (PropertyDescriptor descriptor : descriptors)
			{
				try
				{
					propertyName = descriptor.getName();
					Object val = PropertyUtils.getProperty(source, propertyName);
					if (!StringUtils.isBlank(val.toString()))
					{
						PropertyUtils.setProperty(destination, propertyName, val);
					}
				} catch (Exception ignore)
				{
					// not interested in what we can't read or write
				}
			}
		}
	}

	public static void copyPropertiesExcludingNested(Object destination, Object source)
	{
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(source);
		if (descriptors != null)
		{
			String propertyName = null;
			for (PropertyDescriptor descriptor : descriptors)
			{
				try
				{
					propertyName = descriptor.getName();
					Object val = PropertyUtils.getProperty(source, propertyName);
					if ((val != null) && (!Model.class.isAssignableFrom(val.getClass())))
					{
						PropertyUtils.setProperty(destination, propertyName, val);
					}
				} catch (Exception ignore)
				{
					// not interested in what we can't read or write
				}
			}
		}
	}

}
