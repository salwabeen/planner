;;;;;;;;;;;;;;;
;;; Sokoban ;;;
;;;;;;;;;;;;;;;

(define (domain sokoban)
  (:requirements :strips :typing)
  (:types case)
  (:predicates (isup ?x ?y) (isright ?x ?y) (not-hasblock ?x) (hasblock ?x) (hasguard ?x) (not-hasguard ?x))

  (:action moveUP
	     :parameters (?from - case ?to - case)
	     :precondition (and (isup ?to ?from) (hasguard ?from) (not-hasguard ?to) (not-hasblock ?to))
	     :effect
	     (and 
		   (not (hasguard ?from))
		   (not-hasguard ?from)
		   
		   (not (not-hasguard ?to))
		   (hasguard ?to)))
   
  (:action moveDOWN
	     :parameters (?from - case ?to - case)
	     :precondition (and (isup ?from ?to) (hasguard ?from) (not-hasguard ?to) (not-hasblock ?to))
	     :effect
	     (and 
		   (not (hasguard ?from))
		   (not-hasguard ?from)
		   
		   (not (not-hasguard ?to))
		   (hasguard ?to)))

  (:action moveLEFT
	     :parameters (?from - case ?to - case)
	     :precondition (and (isright ?from ?to) (hasguard ?from) (not-hasguard ?to) (not-hasblock ?to))
	     :effect
	     (and 
		   (not (hasguard ?from))
		   (not-hasguard ?from)
		   
		   (not (not-hasguard ?to))
		   (hasguard ?to)))
		   
  (:action moveRIGHT
	     :parameters (?from - case ?to - case)
	     :precondition (and (isright ?to ?from) (hasguard ?from) (not-hasguard ?to) (not-hasblock ?to))
	     :effect
	     (and 
		   (not (hasguard ?from))
		   (not-hasguard ?from)
		   
		   (not (not-hasguard ?to))
		   (hasguard ?to)))
		   
  (:action pushUP
	     :parameters (?x - case ?from - case ?to - case)
	     :precondition (and (hasguard ?x) (isup ?from ?x) (isup ?to ?from) (hasblock ?from) (not-hasblock ?to))
	     :effect
	     (and  
	           
	           (not (hasguard ?x))
	           (not-hasguard ?x)
	           
	           (not (not-hasguard ?from))
	           (hasguard ?from)
	           
	     	   (not (not-hasblock ?to))
	     	   (hasblock ?to)
	     	   
	     	   (not-hasblock ?from)
	     	   (not (hasblock ?from))
		   
		   ))
   
  (:action pushDOWN
	     :parameters (?x - case ?from - case ?to - case)
	     :precondition (and (hasguard ?x) (isup ?x ?from) (isup ?from ?to) (hasblock ?from) (not-hasblock ?to))
	     :effect
	     (and  
	           
	           (not (hasguard ?x))
	           (not-hasguard ?x)
	           
	           (not (not-hasguard ?from))
	           (hasguard ?from)
	           
	     	   (not (not-hasblock ?to))
	     	   (hasblock ?to)
	     	   
	     	   (not-hasblock ?from)
	     	   (not (hasblock ?from))
		   
		   ))

  (:action pushLEFT
	     :parameters (?x - case ?from - case ?to - case)
	     :precondition (and (hasguard ?x) (isright ?x ?from) (isright ?from ?to) (hasblock ?from) (not-hasblock ?to))
	     :effect
	     (and  
	           
	           (not (hasguard ?x))
	           (not-hasguard ?x)
	           
	           (not (not-hasguard ?from))
	           (hasguard ?from)
	           
	     	   (not (not-hasblock ?to))
	     	   (hasblock ?to)
	     	   
	     	   (not-hasblock ?from)
	     	   (not (hasblock ?from))
		   
		   ))
		   
  (:action pushRIGHT
	     :parameters (?x - case ?from - case ?to - case)
	     :precondition (and (hasguard ?x) (isright ?from ?x) (isright ?to ?from) (hasblock ?from) (not-hasblock ?to))
	     :effect
	     (and  
	           
	           (not (hasguard ?x))
	           (not-hasguard ?x)
	           
	           (not (not-hasguard ?from))
	           (hasguard ?from)
	           
	     	   (not (not-hasblock ?to))
	     	   (hasblock ?to)
	     	   
	     	   (not-hasblock ?from)
	     	   (not (hasblock ?from))
		   
		   ))

  
  )
