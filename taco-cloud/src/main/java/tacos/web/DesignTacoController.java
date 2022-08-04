package tacos.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Taco;
import tacos.model.TacoOrder;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = List.of(
			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
			new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
			new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
			new Ingredient("LETC", "Lettuce", Type.VEGGIES),
			new Ingredient("CHED", "Cheddar", Type.CHEESE),
			new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
			new Ingredient("SLSA", "Salsa", Type.SAUCE),
			new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}
	
	@ModelAttribute(name = "order")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "design")
	public Taco design() {
		return new Taco();
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients
	           .stream()
	           .filter(t -> t.getType().equals(type))
	           .collect(Collectors.toList());
	}

}