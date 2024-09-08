package cruz.gustavo.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.Mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;

public class DozerMapper {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
//	private static ModelMapper mapper = new ModelMapper();
	
	//Este método converte um objeto de uma classe (tipo O, ou seja, a classe de origem) 
	//para um objeto de outra classe (tipo D, ou seja, a classe de destino).
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	//Este método converte uma lista de objetos de uma classe (tipo O) 
	//para uma lista de objetos de outra classe (tipo D)
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		
		for (O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		
		return destinationObjects;
	}
	
	//Esses métodos são muito úteis quando você deseja transformar entidades de banco de dados 
	//(como Entity) em objetos que serão usados na API (como DTO), ou vice-versa
	
}
