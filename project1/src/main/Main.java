package main;

import accauntService.AccauntServiceImpl;
import base.AccauntService;
import base.HandlerIdSession;
import base.WebSocketService;
import base.GameMechanics;
import game.WebSocketServiceImpl;
import game.WebSocketGameServlet;
import mechanics.GameMechanicsImpl;
import servlets.ChatWebServletImpl;
import servlets.SignUpServletImpl;
import servlets.SignInServletImpl;
import servlets.SignOutServletImpl;
import servlets.GameServletImpl;
import chat.WebSocketChatServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.Handler;

public class Main
{
    final static int port = 8080;

    public static void main(String[] args) throws Exception {




        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        AccauntService accauntService = new AccauntServiceImpl();
        HandlerIdSession handlerIdSession = new HandlerIdSessionImpl( accauntService );

        WebSocketService webSocketService = new WebSocketServiceImpl();
        GameMechanics gameMechanics = new GameMechanicsImpl(webSocketService);


        context.addServlet(new ServletHolder(new WebSocketChatServlet(handlerIdSession)), WebSocketChatServlet.path);

        context.addServlet(new ServletHolder(new WebSocketGameServlet(handlerIdSession, gameMechanics, webSocketService)), "/gameplay");

        context.addServlet(new ServletHolder(new GameServletImpl( handlerIdSession )), GameServletImpl.path);
        context.addServlet(new ServletHolder(new ChatWebServletImpl( handlerIdSession )), ChatWebServletImpl.path);
        context.addServlet(new ServletHolder(new SignUpServletImpl(accauntService, handlerIdSession)),SignUpServletImpl.path);
        context.addServlet(new ServletHolder(new SignInServletImpl(accauntService, handlerIdSession)),SignInServletImpl.path);
        context.addServlet(new ServletHolder(new SignOutServletImpl(  handlerIdSession )),SignOutServletImpl.path);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("static");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers( new Handler[] { resource_handler, context });


        Server server = new Server(port);
        server.setHandler(handlers);

        server.start();

        gameMechanics.run();

    }
}
