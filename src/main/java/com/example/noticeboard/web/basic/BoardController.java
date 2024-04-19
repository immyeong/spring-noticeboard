package com.example.noticeboard.web.basic;

import com.example.noticeboard.domain.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/boards")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewListRepository reviewListRepository;


    @GetMapping
    public String boards(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "basic/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable Long boardId, Model model){
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "basic/board";
    }

    @GetMapping("/{boardId}/reviews/{reviewId}")
    public String review(@PathVariable Long boardId, @PathVariable Long reviewId, Model model){
        Board board = boardRepository.findById(boardId);
        Review review = reviewRepository.findById(reviewId);
        model.addAttribute("board", board);
        model.addAttribute("review", review);

        return "basic/review";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String save(Board board, RedirectAttributes redirectAttributes){
        boardRepository.save(board);
        redirectAttributes.addAttribute("boardId", board.getId());
        redirectAttributes.addAttribute("addStatus", true);

        return "redirect:/basic/boards/{boardId}";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable long boardId, Model model){
        Board findBoard = boardRepository.findById(boardId);
        model.addAttribute("board", findBoard);
        return "basic/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable long boardId, @ModelAttribute Board updateBoard, RedirectAttributes redirectAttributes){
        boardRepository.update(boardId, updateBoard);
        redirectAttributes.addAttribute("editStatus", true);
        return "redirect:/basic/boards/{boardId}";
    }

    @GetMapping("/{boardId}/reviews")
    public String reviews(Model model, @PathVariable long boardId){
        Board findBoard = boardRepository.findById(boardId);
        model.addAttribute("board", findBoard);

        List<Review> findReviews = reviewRepository.findAll();
        model.addAttribute("reviews", findReviews);
        return "basic/reviews";
    }

    @GetMapping("/{boardId}/reviews/addReview")
    public String addReviewForm(@PathVariable long boardId, Model model){
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "basic/addReviewForm";
    }

    @PostMapping("/{boardId}/reviews/addReview")
    public String addReview(Review review,
                            RedirectAttributes redirectAttributes){
        reviewRepository.save(review);
        redirectAttributes.addAttribute("reviewId", review.getId());
        redirectAttributes.addAttribute("addStatus", true);
        return "redirect:/basic/boards/{boardId}/reviews/{reviewId}";
    }

    @GetMapping("/{boardId}/reviews/{reviewId}/edit")
    public String editReviewForm(@PathVariable long boardId, @PathVariable long reviewId, Model model){
        Review findReview = reviewRepository.findById(reviewId);
        model.addAttribute("review", findReview);
        return "basic/editReviewForm";
    }

    @PostMapping("/{boardId}/reviews/{reviewId}/edit")
    public String editReview(@PathVariable long boardId, @PathVariable long reviewId, @ModelAttribute Review updateReview, RedirectAttributes redirectAttributes){
        reviewRepository.update(reviewId, updateReview);
        redirectAttributes.addAttribute("editStatus", true);
        return "redirect:/basic/boards/{boardId}/reviews/{reviewId}";
    }

//    @PostMapping("/{boardId}/edit")
//    public String edit(@PathVariable long boardId, @ModelAttribute Board updateBoard, RedirectAttributes redirectAttributes){
//        boardRepository.update(boardId, updateBoard);
//        redirectAttributes.addAttribute("editStatus", true);
//        return "redirect:/basic/boards/{boardId}";
//    }
    @PostConstruct
    public void init(){
        Review review1 = new Review(new Member("홍길동"), "Content1");
        Review review2 = new Review(new Member("홍이동"), "Content2");
        Review review3 = new Review(new Member("홍삼동"), "Content3");
        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);

        List<Review> findReviews = reviewRepository.findAll();
        Integer getCount = reviewRepository.reviewCount();

        ReviewList reviewList = new ReviewList(findReviews, getCount);

        reviewListRepository.save(reviewList);

        boardRepository.save(new Board("Title1", "Contents1",reviewList, new Member("홍길동"),1));
        boardRepository.save(new Board("Title2", "Contents2",reviewList, new Member("홍길동"),2));
    }
}
