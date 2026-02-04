package br.com.moisesdias.keycloak;


import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import java.io.IOException;

@Path("/computadores")
@SecurityScheme(securitySchemeName = "quarkus-oauth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(password = @OAuthFlow(
                tokenUrl = "http://localhost:8180/realms/quarkus/protocol/openid-connect/token"
       )))
@Provider
public class ComputadoresResource implements ContainerRequestFilter, ContainerResponseFilter {

    @GET
    @Path("publico")
    @Produces(MediaType.TEXT_PLAIN)
    public String methodname() {
        return "Hello, public!";
    }

    @GET
    @Path("protegido")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("user")
    @SecurityRequirement(name = "quarkus-oauth")
    public String protegido(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        responseContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        return "Hello, protegido!";
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {

    }
}
