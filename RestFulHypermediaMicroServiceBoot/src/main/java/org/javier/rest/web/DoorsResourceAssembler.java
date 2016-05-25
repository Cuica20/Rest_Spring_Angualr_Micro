/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.javier.rest.web;


import org.javier.rest.model.Door;
import org.javier.rest.model.Game;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
final class DoorsResourceAssembler implements ResourceAssembler<Game, Resources<Resource<Door>>> {

    @Override
    public Resources<Resource<Door>> toResource(Game game) {
        List<Resource<Door>> doors = game.getDoors().stream()
                .map(door -> toResource(game, door)).collect(Collectors.toList());

        Resources<Resource<Door>> resource = new Resources<Resource<Door>>(doors);
        resource.add(linkTo(GamesController.class).slash(game.getId()).slash("doors").withSelfRel());

        return resource;
    }

    private Resource<Door> toResource(Game game, Door door) {
        Resource<Door> resource = new Resource<Door>(door);
        resource.add(linkTo(GamesController.class).slash(game.getId()).slash("doors")
                .slash(door.getId()).withSelfRel());

        return resource;
    }

}
