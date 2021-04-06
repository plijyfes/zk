/* F96_ZK_4595Test.java

	Purpose:
		
	Description:
		
	History:
		Fri Mar 26 16:46:50 CST 2021, Created by jameschu

Copyright (C) 2021 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.zktest.zats.test2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.zkoss.lang.Strings;
import org.zkoss.zktest.zats.WebDriverTestCase;
import org.zkoss.zktest.zats.ztl.JQuery;

/**
 * @author jameschu
 */
public class F96_ZK_4595Test extends WebDriverTestCase {
	@Test
	public void test() {
		connect();
		click(jq("$header1"));
		click(jq("$header2"));
		click(jq("$header3"));
//		click(jq("$header4")); //can't sort with listModelMap -> bug, issue opened ZK-4848
		click(jq("$header5"));
		click(jq("$header6"));
		waitResponse();
		click(jq("@button"));
		waitResponse();
		List<String> resultList = new ArrayList<>();
		JQuery jqCellLabel = jq("$lb1 @listitem @listcell:first-child").find("@label");
		for (int i = 0; i < jqCellLabel.length(); i++)
			resultList.add(jqCellLabel.eq(i).html().trim());
		assertEquals("a,a,b,c,d", Strings.join(resultList.toArray(new String[0])));
		resultList.clear();
		jqCellLabel = jq("$g1 .z-row").find("@label");
		for (int i = 0; i < 3; i++)
			resultList.add(jqCellLabel.eq(i).html().trim());
		assertEquals("data&nbsp;1,data&nbsp;10,data&nbsp;100", Strings.join(resultList.toArray(new String[0])));
		resultList.clear();
		jqCellLabel = jq("$lb2 .z-listcell-content");
		for (int i = 0; i < 3; i++)
			resultList.add(jqCellLabel.eq(i).html().trim());
		assertEquals("data 0,data 00,data 1", Strings.join(resultList.toArray(new String[0])));
		resultList.clear();
		jqCellLabel = jq("$lb3 @listitem @listcell:first-child").find("@label");
		for (int i = 0; i < 3; i++)
			resultList.add(jqCellLabel.eq(i).html().trim());
		assertEquals("type 1,type 10,type 100", Strings.join(resultList.toArray(new String[0])));
		resultList.clear();
		jqCellLabel = jq("$tree .z-treecell-text");
		for (int i = 0; i < 3; i++)
			resultList.add(jqCellLabel.eq(i).html().trim());
		assertEquals("item 1,item 2,item 2-2", Strings.join(resultList.toArray(new String[0])));
	}

}