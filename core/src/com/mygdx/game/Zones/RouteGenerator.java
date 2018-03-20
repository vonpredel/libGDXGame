package com.mygdx.game.Zones;

import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RouteGenerator {

    private static final int ABLE_TO_GO_LEFT = 1;
    private static final int ABLE_TO_GO_RIGHT = 2;
    private static final int ABLE_TO_GO_UP = 3;
    private static final int ABLE_TO_GO_DOWN = 4;

    private final Deque<VisitedRoute> visitedStack;
    private final Set<VisitedRoute> visited;

    public RouteGenerator() {
        this.visitedStack = new ArrayDeque<>();
        this.visited = new HashSet<>();
    }

    private void rewind(final VisitedRoute route) {
        final VisitedRoute lastVisited = this.visitedStack.poll();
        route.X = lastVisited.X;
        route.Y = lastVisited.Y;
    }

    public Route[][] generateRoute(int width, int height) {
        final Route[][] routes = new Route[width][height];

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[0].length; j++) {
                routes[i][j] = new Route();
            }
        }

        final VisitedRoute current = new VisitedRoute(0, 0);

        this.visitedStack.clear();
        this.visited.clear();

        while (visited.size() < (width * height)) {
            List<Integer> possibleDirections = new ArrayList<>();
            if (!(current.X == 0)) {
                possibleDirections.add(ABLE_TO_GO_LEFT);
            }
            if (!(current.X == routes.length - 1)) {
                possibleDirections.add(ABLE_TO_GO_RIGHT);
            }
            if (!(current.Y == 0)) {
                possibleDirections.add(ABLE_TO_GO_UP);
            }
            if (!(current.Y == routes[0].length - 1)) {
                possibleDirections.add(ABLE_TO_GO_DOWN);
            }

            int random = MathUtils.random(0, possibleDirections.size() - 1);
            final Integer randomizedDirection = possibleDirections.get(random);

            routes[current.X][current.Y].isAlreadyPassed = true;

            final VisitedRoute visitedRoute = new VisitedRoute(current);
            visitedStack.add(visitedRoute);
            visited.add(visitedRoute);

            switch (randomizedDirection) {
                case ABLE_TO_GO_LEFT:
                    if (routes[current.X][current.Y].isAlreadyPassed) {
                        this.rewind(current);
                        continue;
                    }
                    routes[current.X][current.Y].LEFT = true;
                    current.X -= 1;
                    routes[current.X][current.Y].RIGHT = true;
                    break;
                case ABLE_TO_GO_RIGHT:
                    if (routes[current.X + 1][current.Y].isAlreadyPassed) {
                        this.rewind(current);
                        continue;
                    }
                    routes[current.X][current.Y].RIGHT = true;
                    current.X += 1;
                    routes[current.X][current.Y].LEFT = true;
                    break;
                case ABLE_TO_GO_UP:
                    if (routes[current.X][current.Y - 1].isAlreadyPassed) {
                        this.rewind(current);
                        continue;
                    }
                    routes[current.X][current.Y].UP = true;
                    current.Y -= 1;
                    routes[current.X][current.Y].DOWN = true;
                    break;
                case ABLE_TO_GO_DOWN:
                    if (routes[current.X][current.Y + 1].isAlreadyPassed) {
                        this.rewind(current);
                        continue;
                    }
                    routes[current.X][current.Y].DOWN = true;
                    current.Y += 1;
                    routes[current.X][current.Y].UP = true;
                    break;
            }
        }

        return routes;
    }

    public class Route {

        private boolean isAlreadyPassed = false;

        private Route() {

        }

        boolean UP = false;
        boolean DOWN = false;
        boolean LEFT = false;
        boolean RIGHT = false;
    }

    private class VisitedRoute {
        int X;
        int Y;

        private VisitedRoute(int x, int y) {
            X = x;
            Y = y;
        }

        private VisitedRoute(final VisitedRoute other) {
            this.X = other.X;
            this.Y = other.Y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            VisitedRoute visitedRoute = (VisitedRoute) o;

            return X == visitedRoute.X && Y == visitedRoute.Y;
        }

        @Override
        public int hashCode() {
            int result = X;
            result = 31 * result + Y;
            return result;
        }
    }

}
