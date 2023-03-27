package peaksoft.service.Impl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.ChequeOneDayWaiterTotalAmountRequest;
import peaksoft.dto.request.ChequeRequest;
import peaksoft.dto.response.ChequePaginationResponse;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.*;
import peaksoft.enums.Role;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ChequeService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChequeSerImpl implements ChequeService {

    private final ChequeRepository chequeRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;




//        List<MenuItem> menuItems = menuItemRepository.findAllById(request.menuItemId());
//        Cheque cheque = new Cheque();
//        int priceAverage = 0;
//        for (MenuItem menuItem : menuItems) {
//            priceAverage += menuItem.getPrice().intValue();
//            menuItem.addCheck(cheque);
//        }
//        User user = userRepository.findById(request.userId())
//                .orElseThrow(() -> new NotFoundException("NOT found"));
//        cheque.setUsers(user);
//        cheque.setCreatedAt(LocalDate.now());
//        cheque.setMenuItems(menuItems);
//        cheque.setPriceAverage(BigDecimal.valueOf(priceAverage));
//        chequeRepository.save(cheque);
//        return SimpleResponse.builder().status(HttpStatus.OK).message("Cheque is saved!").build();


    @Override
    public SimpleResponse save(ChequeRequest request) {
        double count = 0;
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new NotFoundException("User with id: " + request.userId() + " is no exist!"));
        Cheque cheque = new Cheque();
        cheque.setUser(user);
        for (MenuItem menuItem : menuItemRepository.findAllById(request.menuItemId())) {
            cheque.addMenuItem(menuItem);
            count += menuItem.getPrice();
        }
        cheque.setPriceAverage(count);
        cheque.setCreatedAt(LocalDate.now());
        double total = (count * cheque.getUsers().getRestaurant().getService()) / 100;
        cheque.setGrandTotal((int) (count + total));
        cheque.setMenuItems(menuItemRepository.findAllById(request.menuItemId()));
        chequeRepository.save(cheque);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Cheque with id: " + cheque.getId() + " is saved!").build();
    }

    @Override
    public List<ChequeResponse> getAll() {
        List<Cheque> cheques = chequeRepository.findAll();
        List<ChequeResponse> chequeResponses = new ArrayList<>();
        ChequeResponse chequeResponse = new ChequeResponse();
        for (Cheque che : cheques) {
            chequeResponse.setId(che.getId());
            chequeResponse.setFullName(che.getUsers().getFirstName() + che.getUsers().getLastName());
            chequeResponse.setItems(che.getMenuItems());
            chequeResponse.setAveragePrice(che.getPriceAverage());
            chequeResponse.setService(che.getUsers().getRestaurant().getService());
            chequeResponse.setGrandTotal(che.getGrandTotal());
            chequeResponses.add(chequeResponse);
        }
        return chequeResponses;
    }

    @Override
    public ChequeResponse getById(Long id) {
        Cheque che = chequeRepository.findById(id).orElseThrow(() -> new NotFoundException("Cheque with id: " + id + " is no exist!"));
        ChequeResponse chequeResponse = new ChequeResponse();
        chequeResponse.setId(che.getId());
        chequeResponse.setFullName(che.getUsers().getFirstName() + che.getUsers().getLastName());
        chequeResponse.setItems(che.getMenuItems());
        chequeResponse.setAveragePrice(che.getPriceAverage());
        chequeResponse.setService(che.getUsers().getRestaurant().getService());
        chequeResponse.setGrandTotal(che.getGrandTotal());
        return chequeResponse;
    }


    @Override
    public SimpleResponse update(Long id, ChequeRequest request) {
        Cheque cheque = chequeRepository.findById(id).orElseThrow(() -> new NotFoundException("Check with id: " + id + " not found!"));
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new NotFoundException("User with id: " + id + " not found!"));
        List<MenuItem> menuItems = menuItemRepository.findAllById(request.menuItemId());
        cheque.setMenuItems(menuItems);
        cheque.setUser(user);
        chequeRepository.save(cheque);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Cheque with id: " + cheque.getId() + " is successfully updated!").build();
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        chequeRepository.findById(id).orElseThrow(() -> new NotFoundException("Cheque with id: " + id + " is no exist"));
        chequeRepository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Cheque with id: " + id + " is successfully deleted!!").build();
    }

    @Override
    public Double getAllChequesByUser(Long userId) {
        double count = 0;
        for (Cheque cheque : chequeRepository.findAll()) {
            if (cheque.getUsers().getId().equals(userId) && cheque.getCreatedAt().equals(LocalDate.now())) {
                count += cheque.getGrandTotal();
            }
        }
        return count;
    }

    @Override
    public Double getAverageSum(Long restId) {
        return chequeRepository.getAverageSum(restId);
    }

    @Override
    public ChequePaginationResponse getChequeResponse(int page, int size) {
        PageRequest pageable = PageRequest.of(page - 1, size, Sort.by("priceAverage"));
        Page<Cheque> pageCheque = chequeRepository.findAll(pageable);
        ChequePaginationResponse chequePaginationResponse = new ChequePaginationResponse();
        chequePaginationResponse.setChequeList(pageCheque.getContent());
        chequePaginationResponse.setCurrentPage(pageable.getPageNumber() + 1);
        chequePaginationResponse.setPageSize(pageCheque.getTotalPages());
        return chequePaginationResponse;
    }


}
