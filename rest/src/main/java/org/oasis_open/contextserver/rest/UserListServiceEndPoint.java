/**
 * This file is part of Jahia, next-generation open source CMS:
 * Jahia's next-generation, open source CMS stems from a widely acknowledged vision
 * of enterprise application convergence - web, search, document, social and portal -
 * unified by the simplicity of web content management.
 *
 * For more information, please visit http://www.jahia.com.
 *
 * Copyright (C) 2002-2013 Jahia Solutions Group SA. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 * As a special exception to the terms and conditions of version 2.0 of
 * the GPL (or any later version), you may redistribute this Program in connection
 * with Free/Libre and Open Source Software ("FLOSS") applications as described
 * in Jahia's FLOSS exception. You should have received a copy of the text
 * describing the FLOSS exception, and it is also available here:
 * http://www.jahia.com/license
 *
 * Commercial and Supported Versions of the program (dual licensing):
 * alternatively, commercial and supported versions of the program may be used
 * in accordance with the terms and conditions contained in a separate
 * written agreement between you and Jahia Solutions Group SA.
 *
 * If you are unsure which license is appropriate for your use,
 * please contact the sales department at sales@jahia.com.
 */
package org.oasis_open.contextserver.rest;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.jahia.unomi.lists.UserList;
import org.jahia.unomi.services.UserListService;
import org.oasis_open.contextserver.api.PartialList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * @author Christophe Laprun
 */
@WebService
@Produces(MediaType.APPLICATION_JSON)
@CrossOriginResourceSharing(
        allowAllOrigins = true,
        allowCredentials = true
)
public class UserListServiceEndPoint {
    private UserListService userListService;
    private LocalizationHelper localizationHelper;

    public UserListServiceEndPoint() {
        System.out.println("Initializing UserList service endpoint...");
    }


    @WebMethod(exclude = true)
    public void setUserListService(UserListService userListService) {
        this.userListService = userListService;
    }


    @WebMethod(exclude = true)
    public void setLocalizationHelper(LocalizationHelper localizationHelper) {
        this.localizationHelper = localizationHelper;
    }

    @GET
    @Path("/")
    public PartialList<UserList> getLists(@QueryParam("offset") @DefaultValue("0") int offset,
                                          @QueryParam("size") @DefaultValue("50") int size,
                                          @QueryParam("sort") String sortBy) {
        return userListService.getLists(offset, size, sortBy);
    }

    @GET
    @Path("/{listId}")
    public UserList load(@PathParam("listId") String listId) {
        return userListService.load(listId);
    }

    @POST
    @Path("/{listId}")
    public void save(UserList list) {
        userListService.save(list);
    }

    @DELETE
    @Path("/{listId}")
    public void delete(@PathParam("listId") String listId) {
        userListService.delete(listId);
    }
}
