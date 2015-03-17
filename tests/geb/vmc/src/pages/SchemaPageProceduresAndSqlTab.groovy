/* This file is part of VoltDB.
 * Copyright (C) 2008-2015 VoltDB Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package vmcTest.pages

/**
 * This class represents the 'Procedures & SQL' tab of the 'Schema' page (or
 * tab) of the VoltDB Management Center (page), which is the VoltDB web UI
 * (replacing the old Catalog Report).
 */
class SchemaPageProceduresAndSqlTab extends SchemaPage {
    static content = {
	
        procedureName	{ $(class:"tablesorter-header-inner", text:"Procedure Name") }
        parameters		{ $(class:"tablesorter-header-inner", text:"Parameters") }
        partitioning 	{ $(class:"tablesorter-header-inner", text:"Partitioning") }
        rw				{ $(class:"tablesorter-header-inner", text:"R/W") }
        access			{ $(class:"tablesorter-header-inner", text:"Access") }
        attributes		{ $(class:"tablesorter-header-inner", text:"Attributes") }
        
        ascending		{ $(class:"tablesorter-icon icon-chevron-up") }
        descending		{ $(class:"tablesorter-icon icon-chevron-down") }
    }
    static at = {
        proceduresTab.displayed
        proceduresTab.attr('class') == 'active'
        
    }

}
