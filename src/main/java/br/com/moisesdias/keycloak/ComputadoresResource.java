package br.com.moisesdias.keycloak;


import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@Path("/computadores")
@SecurityScheme(securitySchemeName = "backend-service",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(password = @OAuthFlow(
                tokenUrl = "http://localhost:8180/realms/quarkus/protocol/openid-connect/token"
       )))
public class ComputadoresResource {

    @GET
    @Path("publico")
    @Produces(MediaType.TEXT_PLAIN)
    public String publico() {
        return "Hello, public!";
    }

    @GET
    @Path("protegido")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("user")
    @SecurityRequirement(name = "backend-service")
    public String protegido() {
        return "Hello, protegido!";
    }
}
